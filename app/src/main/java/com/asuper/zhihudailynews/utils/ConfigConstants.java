/*
 * This file provided by Facebook is for non-commercial testing and evaluation
 * purposes only.  Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.asuper.zhihudailynews.utils;

import android.content.Context;
import android.net.Uri;

import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class ConfigConstants {
    public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
    public static final String INTENT_URL = "intent_url";
    public static final String INTENT_TITLE = "intent_title";
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;

    /**
     * @param context
     * @return
     */
    public static GenericDraweeHierarchy getGenericDraweeHierarchy(Context context) {
        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(context.getResources())
//            .reset()//重置
//            .setActualImageColorFilter(colorFilter)//颜色过滤
//            .setActualImageFocusPoint(focusPoint)//focusCrop, 需要指定一个居中点
//            .setActualImageMatrix(actualImageMatrix)
//            .setActualImageScaleType(actualImageScaleType)//fresco:actualImageScaleType="focusCrop"缩放类型
//            .setBackground(background)//fresco:backgroundImage="@color/blue"背景图片
//            .setBackgrounds(backgrounds)
//            .setFadeDuration(fadeDuration)//fresco:fadeDuration="300"加载图片动画时间
//            .setFailureImage(ContextCompat.getDrawable(context, R.mipmap.image_failure))//fresco:failureImage="@drawable/error"失败图
//            .setFailureImage(failureDrawable, failureImageScaleType)//fresco:failureImageScaleType="centerInside"失败图缩放类型
//            .setOverlay(overlay)//fresco:overlayImage="@drawable/watermark"叠加图
//            .setOverlays(overlays)
//            .setPlaceholderImage(ContextCompat.getDrawable(context, R.mipmap.image_loading))//fresco:placeholderImage="@color/wait_color"占位图
//            .setPlaceholderImage(placeholderDrawable, placeholderImageScaleType)//fresco:placeholderImageScaleType="fitCenter"占位图缩放类型
//            .setPressedStateOverlay(drawable)//fresco:pressedStateOverlayImage="@color/red"按压状态下的叠加图
                .setProgressBarImage(new ProgressBarDrawable())//进度条fresco:progressBarImage="@drawable/progress_bar"进度条
//            .setProgressBarImage(progressBarImage, progressBarImageScaleType)//fresco:progressBarImageScaleType="centerInside"进度条类型
//            .setRetryImage(retryDrawable)//fresco:retryImage="@drawable/retrying"点击重新加载
//            .setRetryImage(retryDrawable, retryImageScaleType)//fresco:retryImageScaleType="centerCrop"点击重新加载缩放类型
                .setRoundingParams(RoundingParams.asCircle())//圆形/圆角fresco:roundAsCircle="true"圆形
                .build();
        return gdh;
    }


    //图片显示
    public static ImageRequest getImageRequest(SimpleDraweeView view, String uri) {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
//            .setAutoRotateEnabled(true)//自动旋转图片方向
//            .setImageDecodeOptions(getImageDecodeOptions())//  图片解码库
//            .setImageType(ImageType.SMALL)//图片类型，设置后可调整图片放入小图磁盘空间还是默认图片磁盘空间
//            .setLocalThumbnailPreviewsEnabled(true)//缩略图预览，影响图片显示速度（轻微）
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)//请求经过缓存级别  BITMAP_MEMORY_CACHE，ENCODED_MEMORY_CACHE，DISK_CACHE，FULL_FETCH
//            .setPostprocessor(postprocessor)//修改图片
//            .setProgressiveRenderingEnabled(true)//渐进加载，主要用于渐进式的JPEG图，影响图片显示速度（普通）
                .setResizeOptions(new ResizeOptions(view.getLayoutParams().width, view.getLayoutParams().height))//调整大小
//            .setSource(Uri uri)//设置图片地址
                .build();
        return imageRequest;
    }

    public static DraweeController getDraweeController(ImageRequest imageRequest, SimpleDraweeView view) {
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
//            .reset()//重置
                .setAutoPlayAnimations(true)//自动播放图片动画
//            .setCallerContext(callerContext)//回调
//            .setDataSourceSupplier(dataSourceSupplier)//数据源
//            .setFirstAvailableImageRequests(firstAvailableImageRequests)//本地图片复用，可加入ImageRequest数组
                .setImageRequest(imageRequest)//设置单个图片请求～～～不可与setFirstAvailableImageRequests共用，配合setLowResImageRequest为高分辨率的图
//            .setLowResImageRequest(ImageRequest.fromUri(lowResUri))//先下载显示低分辨率的图
                .setOldController(view.getController())//DraweeController复用
                .setTapToRetryEnabled(true)//点击重新加载图
                .build();
        return draweeController;
    }

    public static DraweeController getDraweeController(String uri) {
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)//自动播放图片动画
                .setLowResImageRequest(ImageRequest.fromUri(uri))//先下载显示低分辨率的图
                .setImageRequest(ImageRequest.fromUri(uri))
                .setTapToRetryEnabled(true)//点击重新加载图
                .build();
        return draweeController;
    }

    //图片解码
    public static ImageDecodeOptions getImageDecodeOptions() {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
//            .setBackgroundColor(Color.TRANSPARENT)//图片的背景颜色
//            .setDecodeAllFrames(decodeAllFrames)//解码所有帧
//            .setDecodePreviewFrame(decodePreviewFrame)//解码预览框
//            .setForceOldAnimationCode(forceOldAnimationCode)//使用以前动画
//            .setFrom(options)//使用已经存在的图像解码
//            .setMinDecodeIntervalMs(intervalMs)//最小解码间隔（分位单位）
                .setUseLastFrameForPreview(true)//使用最后一帧进行预览
                .build();
        return decodeOptions;
    }


    public static SimpleDraweeControllerBuilder getSimpleDraweeControllerBuilder(SimpleDraweeControllerBuilder sdcb, Uri uri, Object callerContext, DraweeController draweeController) {
        SimpleDraweeControllerBuilder controllerBuilder = sdcb
                .setUri(uri)
                .setCallerContext(callerContext)
                .setOldController(draweeController);
        return controllerBuilder;
    }


    //DraweeView～～～SimpleDraweeView——UI组件
    public static SimpleDraweeView getSimpleDraweeView(Context context, Uri uri) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setImageURI(uri);
        simpleDraweeView.setAspectRatio(1f);//宽高缩放比
        return simpleDraweeView;
    }

}

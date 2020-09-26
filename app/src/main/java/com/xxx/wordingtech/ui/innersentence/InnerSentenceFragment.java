package com.xxx.wordingtech.ui.innersentence;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ItemSentenceBinding;
import com.xxx.wordingtech.model.listen.Sentence;
import com.xxx.wordingtech.ui.base.commonlist.CommonListFragment;

import java.io.IOException;

/*
 * 句子/影视片段 的 Fragment 句子和影视片段除了长短不一致外其余内容一致，用一个Fragment就可以，他们调用接口时使用classify关键字区分
 * */
public class InnerSentenceFragment extends CommonListFragment<Sentence, InnerSentenceViewModel, ItemSentenceBinding> {

    //科大讯飞语音合成对象
    private MediaPlayer mediaPlayer;

    //正在播放的Item
    private ItemSentenceBinding currentItemBinding;

    public InnerSentenceFragment(String classify) {
        super(classify);
    }

    public InnerSentenceFragment(String classify, Boolean canRefresh) {
        super(classify, canRefresh);
    }


    @Override
    protected Class<InnerSentenceViewModel> getViewModelClazz() {
        return InnerSentenceViewModel.class;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.item_sentence;
    }

    @Override
    protected void initView() {
        super.initView();
        viewModel.classify = classify;
        commonListRecyclerAdapter.setOnCellClickListener((itemBinding, sentence) -> {
                //判断现在有无音频在播放
                if (currentItemBinding != null) {
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }catch (Throwable e){
                        e.printStackTrace();
                    }
                    currentItemBinding.ivVoice.setImageResource(R.drawable.voice_nor);
                    currentItemBinding = null;
                    mediaPlayer = null;
                } else if (currentItemBinding != itemBinding) {//正在播放的不是本身
                    try {
                        //开始播放
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                currentItemBinding.ivVoice.setImageResource(R.drawable.voice_nor);
                                currentItemBinding = null;
                                mediaPlayer = null;
                            }
                        });
                        mediaPlayer.setDataSource("http://dict.youdao.com/speech?audio=" + sentence.getEnglish());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        currentItemBinding = itemBinding;
                        currentItemBinding.ivVoice.setImageResource(R.drawable.voice_active);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                } else {//正在播放的就是本身,取消播放
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    currentItemBinding = null;
                    mediaPlayer = null;
                }

        });

    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("初始化科大讯飞语音合成引擎", "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Log.d("初始化失败,错误码：", code + ",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");

            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    /**
     * 合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            //showTip("开始播放");
            currentItemBinding.ivVoice.setImageResource(R.drawable.voice_active);

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {

        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                currentItemBinding.ivVoice.setImageResource(R.drawable.voice_nor);
                currentItemBinding = null;
                //showTip("播放完成");
            } else if (error != null) {
                //showTip(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int i1, int i2, Bundle bundle) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = bundle.getString(SpeechEvent.KEY_EVENT_AUDIO_URL);
                //Log.d(TAG, "session id =" + sid);
            }

            //实时音频流输出参考
			/*if (SpeechEvent.EVENT_TTS_BUFFER == eventType) {
				byte[] buf = obj.getByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER);
				Log.e("MscSpeechLog", "buf is =" + buf);
			}*/
        }

    };

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null)
            mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
}

package com.ruoyi.aichat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsrRequest {
    private User user;
    private Audio audio;
    private Request request;
    private String callback;
    private String callbackData;

    // 用户配置
    public static class User {
        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }

    // 音频配置
    public static class Audio {
        private String format;
        private String url;
        // 新增：直接携带base64音频数据（flash 极速接口支持）
        private String data;
        private String language;
        private String codec;
        private Integer rate;
        private Integer bits;
        private Integer channel;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

        public Integer getRate() {
            return rate;
        }

        public void setRate(Integer rate) {
            this.rate = rate;
        }

        public Integer getBits() {
            return bits;
        }

        public void setBits(Integer bits) {
            this.bits = bits;
        }

        public Integer getChannel() {
            return channel;
        }

        public void setChannel(Integer channel) {
            this.channel = channel;
        }
    }

    // 请求配置
    public static class Request {
        @JsonProperty("model_name")
        private String modelName = "bigmodel";

        @JsonProperty("model_version")
        private String modelVersion;

        @JsonProperty("enable_itn")
        private Boolean enableItn = true;

        @JsonProperty("enable_punc")
        private Boolean enablePunc = false;

        @JsonProperty("enable_ddc")
        private Boolean enableDdc = false;

        @JsonProperty("enable_speaker_info")
        private Boolean enableSpeakerInfo = false;

        @JsonProperty("enable_channel_split")
        private Boolean enableChannelSplit = false;

        @JsonProperty("show_utterances")
        private Boolean showUtterances = false;

        @JsonProperty("show_speech_rate")
        private Boolean showSpeechRate = false;

        @JsonProperty("show_volume")
        private Boolean showVolume = false;

        @JsonProperty("enable_lid")
        private Boolean enableLid = false;

        @JsonProperty("enable_emotion_detection")
        private Boolean enableEmotionDetection = false;

        @JsonProperty("enable_gender_detection")
        private Boolean enableGenderDetection = false;

        @JsonProperty("vad_segment")
        private Boolean vadSegment = false;

        @JsonProperty("end_window_size")
        private Integer endWindowSize;

        @JsonProperty("sensitive_words_filter")
        private String sensitiveWordsFilter;

        private String corpus;

        @JsonProperty("boosting_table_name")
        private String boostingTableName;

//        private String context;

        public String getModelName() { return modelName; }
        public void setModelName(String modelName) { this.modelName = modelName; }
        public String getModelVersion() { return modelVersion; }
        public void setModelVersion(String modelVersion) { this.modelVersion = modelVersion; }
        public Boolean getEnableItn() { return enableItn; }
        public void setEnableItn(Boolean enableItn) { this.enableItn = enableItn; }
        public Boolean getEnablePunc() { return enablePunc; }
        public void setEnablePunc(Boolean enablePunc) { this.enablePunc = enablePunc; }
        public Boolean getEnableDdc() { return enableDdc; }
        public void setEnableDdc(Boolean enableDdc) { this.enableDdc = enableDdc; }
        public Boolean getEnableSpeakerInfo() { return enableSpeakerInfo; }
        public void setEnableSpeakerInfo(Boolean enableSpeakerInfo) { this.enableSpeakerInfo = enableSpeakerInfo; }
        public Boolean getEnableChannelSplit() { return enableChannelSplit; }
        public void setEnableChannelSplit(Boolean enableChannelSplit) { this.enableChannelSplit = enableChannelSplit; }
        public Boolean getShowUtterances() { return showUtterances; }
        public void setShowUtterances(Boolean showUtterances) { this.showUtterances = showUtterances; }
        public Boolean getShowSpeechRate() { return showSpeechRate; }
        public void setShowSpeechRate(Boolean showSpeechRate) { this.showSpeechRate = showSpeechRate; }
        public Boolean getShowVolume() { return showVolume; }
        public void setShowVolume(Boolean showVolume) { this.showVolume = showVolume; }
        public Boolean getEnableLid() { return enableLid; }
        public void setEnableLid(Boolean enableLid) { this.enableLid = enableLid; }
        public Boolean getEnableEmotionDetection() { return enableEmotionDetection; }
        public void setEnableEmotionDetection(Boolean enableEmotionDetection) { this.enableEmotionDetection = enableEmotionDetection; }
        public Boolean getEnableGenderDetection() { return enableGenderDetection; }
        public void setEnableGenderDetection(Boolean enableGenderDetection) { this.enableGenderDetection = enableGenderDetection; }
        public Boolean getVadSegment() { return vadSegment; }
        public void setVadSegment(Boolean vadSegment) { this.vadSegment = vadSegment; }
        public Integer getEndWindowSize() { return endWindowSize; }
        public void setEndWindowSize(Integer endWindowSize) { this.endWindowSize = endWindowSize; }
        public String getSensitiveWordsFilter() { return sensitiveWordsFilter; }
        public void setSensitiveWordsFilter(String sensitiveWordsFilter) { this.sensitiveWordsFilter = sensitiveWordsFilter; }
        public String getCorpus() { return corpus; }
        public void setCorpus(String corpus) { this.corpus = corpus; }
        public String getBoostingTableName() { return boostingTableName; }
        public void setBoostingTableName(String boostingTableName) { this.boostingTableName = boostingTableName; }
//        public String getContext() { return context; }
//        public void setContext(String context) { this.context = context; }
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Audio getAudio() { return audio; }
    public void setAudio(Audio audio) { this.audio = audio; }
    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }
    public String getCallback() { return callback; }
    public void setCallback(String callback) { this.callback = callback; }
    public String getCallbackData() { return callbackData; }
    public void setCallbackData(String callbackData) { this.callbackData = callbackData; }
}


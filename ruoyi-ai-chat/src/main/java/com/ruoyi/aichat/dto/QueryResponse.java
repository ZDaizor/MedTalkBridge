package com.ruoyi.aichat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResponse {
    private Result result;

    @JsonProperty("audio_info")
    private AudioInfo audioInfo;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String text;
        private List<Utterance> utterances;
        private Additions additions; // 新增字段

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Utterance> getUtterances() {
            return utterances;
        }

        public void setUtterances(List<Utterance> utterances) {
            this.utterances = utterances;
        }

        public Additions getAdditions() {
            return additions;
        }

        public void setAdditions(Additions additions) {
            this.additions = additions;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Additions {
        private String duration;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Utterance {
        private Boolean definite;

        @JsonProperty("end_time")
        private Integer endTime;

        @JsonProperty("start_time")
        private Integer startTime;

        private String text;
        private List<Word> words;

        public Boolean getDefinite() {
            return definite;
        }

        public void setDefinite(Boolean definite) {
            this.definite = definite;
        }

        public Integer getEndTime() {
            return endTime;
        }

        public void setEndTime(Integer endTime) {
            this.endTime = endTime;
        }

        public Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Integer startTime) {
            this.startTime = startTime;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Word> getWords() {
            return words;
        }

        public void setWords(List<Word> words) {
            this.words = words;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Word {
        @JsonProperty("blank_duration")
        private Integer blankDuration;

        @JsonProperty("end_time")
        private Integer endTime;

        @JsonProperty("start_time")
        private Integer startTime;

        private String text;

        // 新增识别返回中的置信度字段
        private Integer confidence;

        public Integer getBlankDuration() {
            return blankDuration;
        }

        public void setBlankDuration(Integer blankDuration) {
            this.blankDuration = blankDuration;
        }

        public Integer getEndTime() {
            return endTime;
        }

        public void setEndTime(Integer endTime) {
            this.endTime = endTime;
        }

        public Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Integer startTime) {
            this.startTime = startTime;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getConfidence() {
            return confidence;
        }

        public void setConfidence(Integer confidence) {
            this.confidence = confidence;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AudioInfo {
        private Integer duration;

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }
    }

    // ========== Getters & Setters ==========

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public AudioInfo getAudioInfo() {
        return audioInfo;
    }

    public void setAudioInfo(AudioInfo audioInfo) {
        this.audioInfo = audioInfo;
    }
}
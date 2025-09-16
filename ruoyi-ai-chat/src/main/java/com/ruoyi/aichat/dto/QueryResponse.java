package com.ruoyi.aichat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QueryResponse {
    private Result result;
    private AudioInfo audioInfo;

    public static class Result {
        private String text;
        private List<Utterance> utterances;

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
    }

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

    public static class Word {
        @JsonProperty("blank_duration")
        private Integer blankDuration;

        @JsonProperty("end_time")
        private Integer endTime;

        @JsonProperty("start_time")
        private Integer startTime;

        private String text;

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
    }

    public static class AudioInfo {
        private Integer duration;

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }
    }

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
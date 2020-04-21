package com.greychain.wootlabproject.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "overview",
        "voteAverage",
        "releaseDate",
        "runtime",
        "title",
        "genres",
        "id",
        "backdropPath",
        "vidoes",
        "budget",
        "posterPath",
        "status",
        "homepage"
})
public class Movie {

    @JsonProperty("overview")
    private String overview;
    @JsonProperty("voteAverage")
    private Double voteAverage;
    @JsonProperty("releaseDate")
    private List<Integer> releaseDate = null;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("title")
    private String title;
    @JsonProperty("genres")
    private List<Genre> genres = null;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("backdropPath")
    private String backdropPath;
    @JsonProperty("vidoes")
    private List<Video> vidoes = null;
    @JsonProperty("budget")
    private Double budget;
    @JsonProperty("posterPath")
    private String posterPath;
    @JsonProperty("status")
    private String status;
    @JsonProperty("homepage")
    private String homepage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("overview")
    public String getOverview() {
        return overview;
    }

    @JsonProperty("overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty("voteAverage")
    public Double getVoteAverage() {
        return voteAverage;
    }

    @JsonProperty("voteAverage")
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @JsonProperty("releaseDate")
    public List<Integer> getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("releaseDate")
    public void setReleaseDate(List<Integer> releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @JsonProperty("runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("genres")
    public List<Genre> getGenres() {
        return genres;
    }

    @JsonProperty("genres")
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("backdropPath")
    public String getBackdropPath() {
        return backdropPath;
    }

    @JsonProperty("backdropPath")
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @JsonProperty("vidoes")
    public List<Video> getVidoes() {
        return vidoes;
    }

    @JsonProperty("vidoes")
    public void setVidoes(List<Video> vidoes) {
        this.vidoes = vidoes;
    }

    @JsonProperty("budget")
    public Double getBudget() {
        return budget;
    }

    @JsonProperty("budget")
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @JsonProperty("posterPath")
    public String getPosterPath() {
        return posterPath;
    }

    @JsonProperty("posterPath")
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("homepage")
    public String getHomepage() {
        return homepage;
    }

    @JsonProperty("homepage")
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
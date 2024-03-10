package com.jp.fusion.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;

import java.util.List;

@Document
@Getter
@Setter
@ToString
public class Movie {
    @IdPrefix private final String prefixEntity = "Movie";

    @Id
    @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES, delimiter = "#")
    private String id;

    @IdAttribute(order = 0)
    private Short year;
    private String title;
    private String extract;
    private String thumbnail;
    private List<String> cast;
    private List<String> genres;
    private String href;

    @IdAttribute(order = 1)
    private String UUId;
}

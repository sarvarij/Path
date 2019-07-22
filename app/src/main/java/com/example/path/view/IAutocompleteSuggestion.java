package com.example.path.view;

public interface IAutocompleteSuggestion<T_SearchResult> {
    String getCaption(T_SearchResult searchResult);
    String getHint(T_SearchResult searchResult);
}

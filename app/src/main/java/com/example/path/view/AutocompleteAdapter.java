package com.example.path.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.path.R;
import com.example.path.models.JAWGFeature;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteAdapter<T_SearchResult> extends ArrayAdapter<String> {

    private List<String> items;
    private List<T_SearchResult> searchResults;
    private IAutocompleteSuggestion autocompleteSuggestion;

    public AutocompleteAdapter(Context context, IAutocompleteSuggestion autocompleteSuggestion) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        this.autocompleteSuggestion = autocompleteSuggestion;
    }

    public void setItems(List<T_SearchResult> searchResults) {
        this.searchResults = searchResults;

        ArrayList<String> locationList = new ArrayList<>(searchResults.size());
        for (T_SearchResult searchResult : searchResults){
            locationList.add(autocompleteSuggestion.getCaption(searchResult));
        }

        items = locationList;

        clear();
        addAll(locationList);

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView == null
                ? LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_autocomplete_list_item, parent, false)
                : convertView;


        ((TextView)result.findViewById(R.id.text_name)).setText(
                autocompleteSuggestion.getCaption(searchResults.get(position)));
        ((TextView)result.findViewById(R.id.text_label)).setText(
                autocompleteSuggestion.getHint(searchResults.get(position)));


        return result;
    }

    private NoFilter noFilter = new NoFilter();
    private class NoFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            filterResults.values = AutocompleteAdapter.this.items;
            filterResults.count = AutocompleteAdapter.this.items.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return noFilter;
    }

    public T_SearchResult getSearchResult(int index){
        return searchResults.get(index);
    }
}

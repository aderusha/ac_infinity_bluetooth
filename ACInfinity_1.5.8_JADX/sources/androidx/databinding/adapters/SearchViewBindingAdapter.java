package androidx.databinding.adapters;

import android.os.Build;
import android.widget.SearchView;

public class SearchViewBindingAdapter {

    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    public static void setOnQueryTextListener(SearchView searchView, final OnQueryTextSubmit onQueryTextSubmit, final OnQueryTextChange onQueryTextChange) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        if (onQueryTextSubmit == null && onQueryTextChange == null) {
            searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) null);
        } else {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                public boolean onQueryTextSubmit(String str) {
                    OnQueryTextSubmit onQueryTextSubmit = OnQueryTextSubmit.this;
                    if (onQueryTextSubmit != null) {
                        return onQueryTextSubmit.onQueryTextSubmit(str);
                    }
                    return false;
                }

                public boolean onQueryTextChange(String str) {
                    OnQueryTextChange onQueryTextChange = onQueryTextChange;
                    if (onQueryTextChange != null) {
                        return onQueryTextChange.onQueryTextChange(str);
                    }
                    return false;
                }
            });
        }
    }

    public static void setOnSuggestListener(SearchView searchView, final OnSuggestionSelect onSuggestionSelect, final OnSuggestionClick onSuggestionClick) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        if (onSuggestionSelect == null && onSuggestionClick == null) {
            searchView.setOnSuggestionListener((SearchView.OnSuggestionListener) null);
        } else {
            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                public boolean onSuggestionSelect(int i) {
                    OnSuggestionSelect onSuggestionSelect = OnSuggestionSelect.this;
                    if (onSuggestionSelect != null) {
                        return onSuggestionSelect.onSuggestionSelect(i);
                    }
                    return false;
                }

                public boolean onSuggestionClick(int i) {
                    OnSuggestionClick onSuggestionClick = onSuggestionClick;
                    if (onSuggestionClick != null) {
                        return onSuggestionClick.onSuggestionClick(i);
                    }
                    return false;
                }
            });
        }
    }
}

package p014io.github.luizgrp.sectionedrecyclerviewadapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection */
public abstract class StatelessSection extends Section {
    public StatelessSection(SectionParameters sectionParameters) {
        super(sectionParameters);
        if (sectionParameters.loadingResourceId != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have a loading state resource");
        } else if (sectionParameters.loadingViewWillBeProvided) {
            throw new IllegalArgumentException("Stateless section shouldn't have loadingViewWillBeProvided set");
        } else if (sectionParameters.failedResourceId != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have a failed state resource");
        } else if (sectionParameters.failedViewWillBeProvided) {
            throw new IllegalArgumentException("Stateless section shouldn't have failedViewWillBeProvided set");
        } else if (sectionParameters.emptyResourceId != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have an empty state resource");
        } else if (sectionParameters.emptyViewWillBeProvided) {
            throw new IllegalArgumentException("Stateless section shouldn't have emptyViewWillBeProvided set");
        }
    }

    public final void onBindLoadingViewHolder(RecyclerView.ViewHolder viewHolder) {
        super.onBindLoadingViewHolder(viewHolder);
    }

    public final RecyclerView.ViewHolder getLoadingViewHolder(View view) {
        return super.getLoadingViewHolder(view);
    }

    public final void onBindFailedViewHolder(RecyclerView.ViewHolder viewHolder) {
        super.onBindFailedViewHolder(viewHolder);
    }

    public final RecyclerView.ViewHolder getFailedViewHolder(View view) {
        return super.getFailedViewHolder(view);
    }

    public final void onBindEmptyViewHolder(RecyclerView.ViewHolder viewHolder) {
        super.onBindEmptyViewHolder(viewHolder);
    }

    public final RecyclerView.ViewHolder getEmptyViewHolder(View view) {
        return super.getEmptyViewHolder(view);
    }
}

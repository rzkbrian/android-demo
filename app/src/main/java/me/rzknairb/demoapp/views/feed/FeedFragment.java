package me.rzknairb.demoapp.views.feed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.rzknairb.demoapp.R;
import me.rzknairb.demoapp.views.BaseFragment;
import me.rzknairb.demoapp.views.BasePresenter;
import me.rzknairb.demoapp.views.comments.CommentActivity;
import me.rzknairb.domain.entities.Feed;


public class FeedFragment extends BaseFragment implements FeedRecyclerViewAdapter.OnListFragmentInteractionListener, FeedPresenter.View {

    public FeedFragment() {
    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Inject
    FeedPresenter presenter;



    @BindView(R.id.recycler_feed)
    RecyclerView recyclerView;

    private FeedRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, view);
        presenter.start();

        adapter = new FeedRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListFragmentInteraction(Feed item) {
        startActivity(CommentActivity.getCallIntent(getContext(), item));
    }

    @Override
    public void feedReady(List<Feed> feed) {
        adapter.setList(feed);
    }

    @Override
    public void onErrorList() {
        Toast.makeText(this.getContext(), "Network Error", Toast.LENGTH_LONG).show();
    }
}

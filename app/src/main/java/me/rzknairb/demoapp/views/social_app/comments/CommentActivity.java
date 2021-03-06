package me.rzknairb.demoapp.views.social_app.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.rzknairb.demoapp.R;
import me.rzknairb.demoapp.views.BaseActivity;
import me.rzknairb.demoapp.views.social_app.user_profile.UserProfileActivity;
import me.rzknairb.domain.entities.Feed;

public class CommentActivity extends BaseActivity implements CommentPresenter.View, CommentRecyclerViewAdapter.OnClickProfileListener {

    private static final String PARAM_POST = "FEED";

    public static Intent getCallIntent(Context context, Feed post) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(CommentActivity.PARAM_POST, new Gson().toJson(post, Feed.class));
        return intent;
    }

    @Inject
    CommentPresenter presenter;

    private CommentRecyclerViewAdapter adapter;

    @BindView(R.id.recycler_comments)
    RecyclerView recyclerView;
    @BindView(R.id.et_new_comment)
    EditText newCommnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        initToolbar();
        initViews();
        presenter.start(getIntent().getStringExtra(PARAM_POST));
    }

    private void initViews() {
        adapter = new CommentRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onDataReady(Feed post) {
        adapter.setList(post.getComment());
    }

    @Override
    public void onError() {
        Toast.makeText(this, "onError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentIsEmpty() {
        Toast.makeText(getApplicationContext(), getString(R.string.input_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewComment(Feed.Comment comment) {
        adapter.addComment(comment);
        newCommnet.getText().clear();
    }

    @Override
    public void goToUserProfile(String idProfile) {
        startActivity(UserProfileActivity.getCallIntent(this, idProfile));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickProfile(String idProfile) {
        presenter.onClickProfile(idProfile);
    }

    @OnClick(R.id.btn_comment_send)
    public void onClickNewComment(View v) {
        presenter.onNewComment(newCommnet.getText().toString());
    }
}

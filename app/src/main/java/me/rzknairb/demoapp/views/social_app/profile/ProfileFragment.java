package me.rzknairb.demoapp.views.social_app.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.rzknairb.demoapp.R;
import me.rzknairb.demoapp.views.BaseFragment;
import me.rzknairb.domain.entities.User;

public class ProfileFragment extends BaseFragment implements ProfilePresenter.View {

    private static final String TAG = ProfileFragment.class.getSimpleName();
    @Inject
    ProfilePresenter profilePresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.profile_fullname)
    TextView tvFullname;
    @BindView(R.id.profile_image)
    CircleImageView imageView;
    @BindView(R.id.profile_ibPhone)
    ImageButton ivPhone;
    @BindView(R.id.profile_ibEmail)
    ImageButton ivEmail;

    @BindView(R.id.profile_likes)
    TextView likes;
    @BindView(R.id.profile_posts)
    TextView posts;
    @BindView(R.id.profile_shares)
    TextView shares;
    @BindView(R.id.profile_friends)
    TextView friends;

    @BindView(R.id.profile_years)
    TextView years;
    @BindView(R.id.profile_email)
    TextView email;
    @BindView(R.id.profile_location)
    TextView location;
    @BindView(R.id.profile_occupation)
    TextView occupation;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews() {
        ivEmail.setVisibility(android.view.View.GONE);
        ivPhone.setVisibility(android.view.View.GONE);

        years.setText("");
        email.setText("");
        location.setText("");
        occupation.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        profilePresenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onLoadProfile(User user) {
        if (!user.getImage().trim().isEmpty() && getContext() != null){
            Glide.with(getContext())
                    .load(user.getImage())
                    .centerCrop()
                    .into(imageView);
        }
        tvFullname.setText(String.format("%s %s", user.getName(), user.getLastname()));
        years.setText(user.getAge());
        email.setText(user.getEmail());
        location.setText(user.getLocation());
        occupation.setText(user.getOccupation());

        likes.setText(user.getSocial().getLikes());
        posts.setText(user.getSocial().getPosts());
        friends.setText(user.getSocial().getFriends());
        shares.setText(user.getSocial().getShares());
    }

    @Override
    public void onErrorProfile() {
        Toast.makeText(this.getContext(), "Error al cargar Perfil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showProgressBar();
    }

    @Override
    public void hideLoading() {
        hideProgressBar();
    }
}

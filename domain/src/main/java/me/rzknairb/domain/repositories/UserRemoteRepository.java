package me.rzknairb.domain.repositories;

import java.util.List;

import io.reactivex.Single;
import me.rzknairb.domain.entities.User;

public interface UserRemoteRepository {

    Single<User> getProfile();

    Single<List<User>> getUsers();

    Single<User> getUserById(String userId);

}

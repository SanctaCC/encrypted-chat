package com.sanctaultras.auth.user;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>,
        QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
    Optional<User> findOneByEmail(String email);

    @Override
    default void customize(QuerydslBindings bindings, QUser root) {
        bindings.excludeUnlistedProperties(true); //avoid security issues, only query by secure properties
        bindings.including(root.email, root.date);
        bindings.bind(root.email).first(StringExpression::startsWithIgnoreCase);
    }

}
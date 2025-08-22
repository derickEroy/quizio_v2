package com.dak.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.dak.db.Database;
import com.dak.db.tables.UserTable;
import com.dak.exceptions.DatabaseReadException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public class UserModel {
    private final UUID id;
    private final Instant createdAt;
    private final Instant updatedAt;

    private String username;
    private String password;

    public UserModel(UUID id, String username, @NotNull String password, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.username = username;
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean comparePassword(@NotNull String plainString) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainString.toCharArray(), this.password.toCharArray());
        return result.verified;
    }

    public UUID getId() {
        return this.id;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newValue) {
        this.username = newValue;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newValue) {
        this.password = newValue;
    }

    public static @Nullable UserModel findById(String id) {
        String query = String.format("SELECT * FROM %s WHERE id = ?", UserTable.TABLE_NAME);

        try (
                Connection connection = Database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    UUID rsId = UUID.fromString(resultSet.getString(UserTable.ID));
                    String username = resultSet.getString(UserTable.USERNAME);
                    String password = resultSet.getString(UserTable.PASSWORD);
                    Instant createdAt = resultSet.getTimestamp(UserTable.CREATED_AT).toInstant();
                    Instant updatedAt = resultSet.getTimestamp(UserTable.UPDATED_AT).toInstant();

                    return new UserModel(rsId, username, password, createdAt, updatedAt);
                } else {
                    return null;
                }
            }
        } catch (SQLException error) {
            throw new DatabaseReadException(error.getMessage());
        }
    }
}

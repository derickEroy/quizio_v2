package com.dak.models;

import com.dak.db.Database;
import com.dak.db.tables.CategoryTable;
import com.dak.mappers.CategoryMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryModel {
    private final UUID id;

    private final String name;
    private final String image;

    public CategoryModel(UUID id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public static @NotNull List<CategoryModel> findAll() {
        String query = String.format("SELECT * FROM %s", CategoryTable.TABLE_NAME);

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ArrayList<CategoryModel> arrayList = new ArrayList<>();

            while (resultSet.next()) {
                arrayList.add(CategoryMapper.toModel(resultSet));
            }

            return arrayList;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public static @Nullable CategoryModel findById(String id) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s = ? LIMIT 1",
                CategoryTable.TABLE_NAME,
                CategoryTable.ID
        );

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return CategoryMapper.toModel(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}

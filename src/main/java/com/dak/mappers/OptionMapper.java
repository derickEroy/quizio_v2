package com.dak.mappers;

import com.dak.db.tables.OptionTable;
import com.dak.models.OptionModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OptionMapper {
    @Contract("_ -> new")
    public static @NotNull OptionModel toModel(@NotNull ResultSet resultSet) throws SQLException {
        return new OptionModel(
            UUID.fromString(resultSet.getString(OptionTable.ID)),
            UUID.fromString(resultSet.getString(OptionTable.QUESTION_ID)),
            resultSet.getString(OptionTable.TEXT),
            resultSet.getBoolean(OptionTable.IS_CORRECT)
        );
    }
}

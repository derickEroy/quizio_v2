package com.dak.models;

import com.dak.db.Database;
import com.dak.db.tables.OptionTable;
import com.dak.mappers.OptionMapper;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OptionModel {
    private final UUID id;
    private final UUID questionId;

    private boolean isCorrect;
    private String text;

    public OptionModel(UUID id, UUID questionId, String text, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public UUID getId() {
        return id;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getText() {
        return text;
    }

    public static @NotNull List<OptionModel> findManyByQuestionId(@NotNull UUID id) {
        List<OptionModel> optionModels = new ArrayList<>();

        String sql = String.format(
            "SELECT * FROM `%s` WHERE %s = ?",
            OptionTable.TABLE_NAME,
            OptionTable.QUESTION_ID
        );

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    optionModels.add(OptionMapper.toModel(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return optionModels;
    }
}

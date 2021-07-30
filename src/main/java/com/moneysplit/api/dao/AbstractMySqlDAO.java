package com.moneysplit.api.dao;

import static com.opengamma.elsql.ElSqlConfig.*;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.moneysplit.api.model.BaseIdModel;
import com.opengamma.elsql.ElSqlBundle;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public abstract class AbstractMySqlDAO {

    private final ElSqlBundle elSqlBundle;
    protected final NamedParameterJdbcTemplate jdbcTemplate;

    public AbstractMySqlDAO(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.elSqlBundle = ElSqlBundle.of(MYSQL, this.getClass());
    }

    public String toSqlString(String fragmentName) {
        return this.elSqlBundle.getSql(fragmentName);
    }

    public String toSqlString(String fragmentName, SqlParameterSource paramSource) {
        return this.elSqlBundle.getSql(fragmentName, paramSource);
    }

    public MapSqlParameterSource toParamSource(String key, Object value) {
        return new MapSqlParameterSource(key, value);
    }

    protected <T extends BaseIdModel> Optional<T> findById(String fragmentName, SqlParameterSource paramSource,
            RowMapper<T> rowMapper) {
        try {
            return Optional
                    .of(jdbcTemplate.queryForObject(toSqlString(fragmentName, paramSource), paramSource, rowMapper));
        } catch (DataAccessException dae) {
            return Optional.empty();
        }
    }

    protected <T extends BaseIdModel> List<T> findAll(String fragmentName, RowMapper<T> rowMapper) {

        return jdbcTemplate.query(toSqlString(fragmentName), rowMapper);

    }

}

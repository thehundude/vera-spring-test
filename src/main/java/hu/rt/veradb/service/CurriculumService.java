package hu.rt.veradb.service;

import hu.rt.veradb.data.Curricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CurriculumService {

    private static class CurriculumRowMapper implements RowMapper<Curricula> {
        @Override
        public Curricula mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
            final Curricula curricula = new Curricula();
            curricula.setProgram(resultSet.getString("program"));
            curricula.setYear(resultSet.getInt("year"));
            curricula.setUrl(resultSet.getString("url"));
            return curricula;
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Curricula> getCurriculum() {
        final String query = "SELECT * FROM curriculum ORDER BY program, year";
        List<Curricula> curriculaList = jdbcTemplate.query(query, new CurriculumRowMapper());
        return curriculaList;
    }

    @Transactional
    public void addCurricula(Curricula curricula) {
        final String query = "INSERT INTO curriculum (program, year, url) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, curricula.getProgram(), curricula.getYear(), curricula.getUrl());
    }

    @Transactional
    public void updateCurricula(Curricula curricula) {
        final String query = "UPDATE curriculum SET url = ? WHERE program = '" + curricula.getProgram() +
                "' AND year = '" + curricula.getYear() + "'";
        jdbcTemplate.update(query, curricula.getUrl());
    }

    @Transactional
    public void deleteCurricula(Curricula curricula) {
        final String query = "DELETE FROM curriculum WHERE program = '" + curricula.getProgram() +
                "' AND year = '" + curricula.getYear() + "' AND url = '" + curricula.getUrl() + "'";
        jdbcTemplate.update(query);
    }
}

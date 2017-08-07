package hu.rt.veradb.service;

import hu.rt.veradb.data.Curriculum;
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

    private static class CurriculumRowMapper implements RowMapper<Curriculum> {
        @Override
        public Curriculum mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
            final Curriculum curriculum = new Curriculum();
            curriculum.setProgram(resultSet.getString("program"));
            curriculum.setYear(resultSet.getInt("year"));
            curriculum.setUrl(resultSet.getString("url"));
            return curriculum;
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Curriculum> getCurricula() {
        final String query = "SELECT * FROM curriculum ORDER BY program, year";
        List<Curriculum> curriculumList = jdbcTemplate.query(query, new CurriculumRowMapper());
        return curriculumList;
    }

    @Transactional
    public void addCurriculum(Curriculum curriculum) {
        final String query = "INSERT INTO curriculum (program, year, url) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, curriculum.getProgram(), curriculum.getYear(), curriculum.getUrl());
    }

    @Transactional
    public void updateCurriculum(Curriculum curriculum) {
        final String query = "UPDATE curriculum SET url = ? WHERE program = '" + curriculum.getProgram() +
                "' AND year = '" + curriculum.getYear() + "'";
        jdbcTemplate.update(query, curriculum.getUrl());
    }

    @Transactional
    public void deleteCurriculum(Curriculum curriculum) {
        final String query = "DELETE FROM curriculum WHERE program = '" + curriculum.getProgram() +
                "' AND year = '" + curriculum.getYear() + "' AND url = '" + curriculum.getUrl() + "'";
        jdbcTemplate.update(query);
    }
}

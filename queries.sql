-- Part 1: Test it with SQL

-- Part 2: Test it with SQL
SELECT name FROM employer Where location = "St. Louis City";
-- Part 3: Test it with SQL
DROP TABLE job;
-- Part 4: Test it with SQL
SELECT * FROM skill INNER JOIN job_skills ON skill.id = job_skills.skills_id WHERE job_skills.jobs_id IS NOT NULL ORDER BY name ASC;
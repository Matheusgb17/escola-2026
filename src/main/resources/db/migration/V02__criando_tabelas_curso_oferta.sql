CREATE TABLE course (
                        id BIGINT AUTO_INCREMENT NOT NULL,
                        name VARCHAR(255),
                        img_uri VARCHAR(255),
                        img_gray_uri VARCHAR(255),
                        PRIMARY KEY (id)
);

CREATE TABLE offer (
                       id BIGINT AUTO_INCREMENT NOT NULL,
                       edition VARCHAR(255),
                       start_moment TIMESTAMP,
                       end_moment TIMESTAMP,
                       course_id BIGINT,
                       PRIMARY KEY (id),
                       CONSTRAINT fk_offer_course FOREIGN KEY (course_id) REFERENCES course(id)
);
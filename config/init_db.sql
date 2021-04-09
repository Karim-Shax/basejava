CREATE TABLE resume
(
    uuid      CHAR(36) PRIMARY KEY NOT NULL,
    full_name TEXT                 NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);
CREATE
UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);

CREATE TABLE section
(
    uuid        CHAR(36) PRIMARY KEY NOT NULL,
    resume_uuid CHAR(36)             NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    describe    TEXT                 NOT NULL,
    url         TEXT
)

CREATE TABLE organization_section
(
    resume_uuid CHAR(36)             NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    uuid        CHAR(36) PRIMARY KEY NOT NULL
)

CREATE TABLE organization
(
    ors_uuid CHAR(36) NOT NULL REFERENCES organization_section (uuid) ON DELETE CASCADE,
    link     CHAR(36) NOT NULL REFERENCES section (uuid) ON DELETE CASCADE,
    uuid     CHAR(36) NOT NULL PRIMARY KEY
)

CREATE TABLE period
(
    org_uuid      CHAR(36) NOT NULL REFERENCES organization (uuid) ON DELETE CASCADE,
    title         CHAR(50) NOT NULL,
    start_time    DATE     NOT NULL,
    end_time      DATE     NOT NULL,
    tech_describe TEXT
)
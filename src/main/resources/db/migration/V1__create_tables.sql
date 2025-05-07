CREATE TABLE users (
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	name varchar(255) NOT NULL,
	email varchar(255) UNIQUE NOT NULL,
	password varchar(60) NOT NULL,
  created_at timestamp NOT NULL DEFAULT now()
);

CREATE TABLE projects (
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	name varchar(255) NOT NULL,
	description text,
	owner_id uuid NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  created_at timestamp NOT NULL DEFAULT now()
);

CREATE TYPE statuses AS ENUM('pending', 'in-progress', 'done');

CREATE TABLE tasks (
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	title varchar(255) NOT NULL,
	description text,
	status statuses NOT NULL DEFAULT 'pending',
	due_date date,
	asignee_id uuid REFERENCES users(id) ON DELETE CASCADE,
	project_id uuid NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
  created_at timestamp NOT NULL DEFAULT now()
);

CREATE TYPE roles AS ENUM('owner', 'collaborator');

CREATE TABLE project_members (
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	project_id uuid NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
	user_id uuid NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  role roles NOT NULL
);

CREATE TABLE attachments (
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	task_id uuid NOT NULL REFERENCES tasks(id) ON DELETE CASCADE,
	filename varchar(255) NOT NULL,
  url varchar(512) NOT NULL
);

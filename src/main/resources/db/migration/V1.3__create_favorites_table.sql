CREATE TABLE `favorites`  (
       `id` bigint not null auto_increment  primary key ,
        `movie_id` bigint,
        `user_id` bigint
    ) ;
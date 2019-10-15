# SuiviRecherches

## DB
http://localhost:8080/h2-console

## Swagger API
http://localhost:8080/SuiviRecherches/v2/api-docs
http://localhost:8080/SuiviRecherches/swagger-ui.html#/

## Dockerisation
1 Build local
docker build -t matkasdocker/sb-suivi-recherches-aws .
1.1 Run local
docker run --rm -d -p 8080:8080 matkasdocker/sb-suivi-recherches-aws:latest
1.2 Active containers
docker container ls
1.3 Stop container
docker container stop <container id>
1.4 Run sh on in a container
docker exec -it c1819be8ec61 /bin/sh
2 Push to docker.io
docker push matkasdocker/sb-suivi-recherches-aws
3 Run
docker run --rm -d -p 8080:8080 matkasdocker/sb-suivi-recherches-aws:latest

## AWS EC2
1 Connect
ssh -i "~/.ssh/firstKeyAws.pem" ec2-user@ec2-3-19-239-63.us-east-2.compute.amazonaws.com
2 update yum
sudo yum update -y
3 install docker
sudo yum install docker -y
4 start docker
sudo service docker start
5 Run container
docker run --rm -d -p 8080:8080 matkasdocker/sb-suivi-recherches-aws:latest

##Default Users
INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
INSERT INTO user (id, email, password) VALUES (1, 'lambda@test', '$2a$10$804o5kw5hkf66CUn5o0IT.0dPpojja0zm9gElWb5p72nfPQafLk16');
INSERT INTO user (id, email, password) VALUES (2, 'admin@test', '$2a$10$Ajs6i6CJddRfCIi3q3iu4.sFnWaxiAxJEhc3df.2V/IzEoF1xRJge');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

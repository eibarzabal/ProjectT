0.- Navigate to the folder where you want to clone the project

1.- Run the following command to download the project:
git clone https://github.com/eibarzabal/ProjectT.git

2.- Change directory to ProjectT:
cd ProjectT

3.- Package the project (in linux):
./mvnw clean package
[or mvnw.cmd clean package in windows]

4.- Create docker image:
docker build -f Dockerfile -t project_t .

5- Run the image
docker run -p 5000:5000 project_t


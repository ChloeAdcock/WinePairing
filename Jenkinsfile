pipeline {
   agent any

   tools {
         maven 'maven'
   }

   stages {
          stage('Build and deploy') {
                steps {
                     git branch: 'deployable-dev', url: 'https://github.com/ChloeAdcock/WinePairing.git'
                     sh "mvn clean deploy"
                }
                post {
                      success {
                      archiveArtifacts 'target/*.jar'
                     }
                }
          }
          stage('Run BE in docker') {
             steps   {
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-35-177-230-135.eu-west-2.compute.amazonaws.com "docker stop \$(docker ps -a -q); docker rm \$(docker ps -a -q); docker rmi wines; sudo rm wine.jar; wget --output-document wine.jar http://3.11.84.155:8081/service/rest/v1/search/assets/download?repository=wine-hosted\\&group=com.qa.bae\\&name=WinePairing\\&0.0.1-SNAPSHOT\\&sort=version\\&maven.extension=jar; docker build -t wine-app .; docker run -dit --restart unless-stopped -d -p 9090:8081 --name wine-app wines:latest"'
                }
          }
     }
}


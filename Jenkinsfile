pipeline {
   agent any

   tools {
         maven 'maven'
   }

   stages {
          stage('Build and deploy BE') {
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
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-35-177-230-135.eu-west-2.compute.amazonaws.com "./docker.sh"'
                }
          }
          stage('Run FE in docker') {
                steps {
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-35-177-223-218.eu-west-2.compute.amazonaws.com "./dockerfe.sh"'
                }
          }
          stage('Run selenium') {
               steps {
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-35-177-223-218.eu-west-2.compute.amazonaws.com "./selenium.sh"'
               }
          }
     }
}


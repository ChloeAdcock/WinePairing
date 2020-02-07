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
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-18-130-226-251.eu-west-2.compute.amazonaws.com "./docker.sh"'
                }
          }
          stage('Run FE in docker') {
                steps {
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-3-9-164-172.eu-west-2.compute.amazonaws.com "./dockerfe.sh"'
                }
          }
          stage('Run selenium') {
               steps {
                     sh 'ssh -t -i "Access-Key.pem" ubuntu@ec2-3-9-164-172.eu-west-2.compute.amazonaws.com "./selenium.sh"'
               }
          }
     }
}


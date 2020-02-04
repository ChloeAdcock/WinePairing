pipeline {
   agent any

   tools {
      maven 'maven'
   }

   stages {
      stage('Build') {
         steps {
            git 'https://github.com/ChloeAdcock/WinePairing.git'

            sh "mvn clean package"
         }

         post {
            success {
               archiveArtifacts '/var/jenkins_home/workspace/back-end-dev/target/WinePairing/*.war'
            }
         }
      }
   }
}

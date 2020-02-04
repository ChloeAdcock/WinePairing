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
               archiveArtifacts 'target/*.war'
            }
         }
      }
   }
}

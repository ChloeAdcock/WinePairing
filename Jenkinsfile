pipeline {
   agent any

   tools {
      maven 'maven'
   }

   stages {
      stage('Build') {
         steps {
            git 'https://github.com/ChloeAdcock/WinePairing.git'

            sh "mvn clean install"
         }

         post {
            success {
               archiveArtifacts 'target/*.jar'
            }
         }
      }
   }
}

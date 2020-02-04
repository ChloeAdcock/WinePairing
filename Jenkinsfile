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
   }
}


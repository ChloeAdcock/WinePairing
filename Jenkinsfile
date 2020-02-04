pipeline {
   agent any

   tools {
      maven 'maven'
   }

   stages {
      stage('Build and deploy') {
         steps {

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


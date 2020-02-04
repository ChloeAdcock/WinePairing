pipeline {
   agent any

   tools {
      maven 'maven'
   }

   stages {
      stage('Build') {
         steps {

            sh "mvn clean package"
         }

         post {
            success {
               archiveArtifacts 'target/*.jar'
            }
         }
      }
   }
}


node {

    
    stage('Clone and Build') {
      git branch: 'master', url: 'https://github.com/ravisreddy/app.git' 
      
      def mvn_version = 'M3'
withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
  sh "mvn clean package"
}
    }    
  
	 stage('Sonar scan execution') {
        // Run the sonar scan
        steps {
            script {
                def mvnHome = tool 'M3'
                withSonarQubeEnv {

                    sh "'${mvnHome}/bin/mvn'  verify sonar:sonar -Dintegration-tests.skip=true -Dmaven.test.failure.ignore=true"
                }
            }
        }
    }  
    
    stage('Sonar scan result check') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    retry(3) {
                        script {
                            def qg = waitForQualityGate()
                            if (qg.status != 'OK') {
                                error "Pipeline aborted due to quality gate failure: ${qg.status}"
                            }
                        }
                    }
                }
            }
        }
  
    stage("Test image") {
        steps {
            sh "docker-composer build"
            sh "docker-compose up -d"
        }
    }
    
 
}
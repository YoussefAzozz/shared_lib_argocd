def call(String imageName, String credentialsId) {
    withCredentials([usernamePassword(credentialsId: '39efdf06-f686-4219-8117-a9efd32f0c16', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh """
            docker build -t ${imageName}:${BUILD_NUMBER} .
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
            docker push ${imageName}:${BUILD_NUMBER}
        """
    }
}

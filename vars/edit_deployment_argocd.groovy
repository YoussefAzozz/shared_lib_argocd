def call(String image_name) {
  sshagent(['jenkins-key-git']) {
    sh '''
      git clone https://github.com/YoussefAzozz/argocd-lab.git
    '''
    dir('argocd-lab') {
    sh """
    mv argocd-lab argocd-lab-${BUILD_NUMBER}
    # Modify the image line in deployment.yml
    sed -i 's|replicas:.*|replicas: 3|g' deployment.yml
    sed -i 's|image:.*|image: "${image_name}:${BUILD_NUMBER}"|g' deployment.yml
    cat deployment.yml
    """
  }
  }
}

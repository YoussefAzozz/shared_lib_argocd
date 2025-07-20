def call(String image_name) {
  sshagent(['jenkins-key-git']) {
    sh '''
      git clone https://github.com/YoussefAzozz/argocd-lab.git
    '''
    dir('argocd-lab') {
    sh """
    # Modify the image line in deployment.yml
    cd overlays/prod
    sed -i 's|name: ${image_name}|name: ${image_name}|' kustomization.yml
    sed -i 's|newTag: "36"|newTag: "${BUILD_NUMBER}"|' kustomization.yml
    sed -i 's|replicas: 2|replicas: 4|' patch.yml
    cat patch.yml
    cat kustomization.yml
    """
  }
  }
}

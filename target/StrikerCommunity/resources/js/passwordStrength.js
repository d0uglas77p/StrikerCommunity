function clearCadastroModal() {
            PF('cadastroDialog').jq.find('input, textarea').val('');
        }

        function clearRecuperarSenhaModal() {
            PF('recuperarSenhaDialog').jq.find('input, textarea').val('');
        }

function checkPasswordStrength(password) {
    var strength = 0;
    var strengthText = "";

    // Calcular a força da senha
    if (password.length >= 8) {
        strength += 1;
    }
    if (password.match(/[a-z]+/)) {
        strength += 1;
    }
    if (password.match(/[A-Z]+/)) {
        strength += 1;
    }
    if (password.match(/[0-9]+/)) {
        strength += 1;
    }
    if (password.match(/[\W_]+/)) {
        strength += 1;
    }

    // Determinar o texto de força da senha
    switch (strength) {
        case 0:
            strengthText = "Fraca";
            break;
        case 1:
            strengthText = "Moderada";
            break;
        case 2:
            strengthText = "Boa";
            break;
        case 3:
            strengthText = "Muito Boa";
            break;
        case 4:
            strengthText = "Excelente";
            break;
        default:
            strengthText = "Fraca";
    }

    // Atualizar o texto e a cor da força da senha
    document.getElementById('password-strength').innerText = "Força da Senha: " + strengthText;
    document.getElementById('password-strength').style.color = getColorByStrength(strength);

}

function getColorByStrength(strength) {
    switch (strength) {
        case 0:
            return "red";
        case 1:
            return "orange";
        case 2:
            return "yellow";
        case 3:
            return "lightgreen";
        case 4:
            return "green";
        default:
            return "red";
    }
}

// Adicionar evento para verificar a força da senha enquanto o usuário digita
document.getElementById('senha').addEventListener('input', function() {
    checkPasswordStrength(this.value);
});



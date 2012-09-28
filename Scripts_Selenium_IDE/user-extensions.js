
/*
 * Credito da funcao de geracao de CPF: Maurício Avellar
 * http://www.seuenium.com.br/2009/05/20/novos-comandos-do-selenium-para-gerar-cpf-e-cnpj/
 */
Selenium.prototype.doGenerateCPF = function(locator) {
	var element = this.page().findElement(locator);
	var n1 = Math.floor(Math.random()*10);
	var n2 = Math.floor(Math.random()*10);
	var n3 = Math.floor(Math.random()*10);
	var n4 = Math.floor(Math.random()*10);
	var n5 = Math.floor(Math.random()*10);
	var n6 = Math.floor(Math.random()*10);
	var n7 = Math.floor(Math.random()*10);
	var n8 = Math.floor(Math.random()*10);
	var n9 = Math.floor(Math.random()*10);

	var d1 = (n1*10)+(n2*9)+(n3*8)+(n4*7)+(n5*6)+(n6*5)+(n7*4)+(n8*3)+(n9*2);
	d1 = (11-(d1%11));
	if(d1 >= 10) { 
		d1 = 0;
	}
	var d2 = (n1*11)+(n2*10)+(n3*9)+(n4*8)+(n5*7)+(n6*6)+(n7*5)+(n8*4)+(n9*3)+(d1*2);
	d2 = (11-(d2%11));
	if(d2 >= 10) {
		d2 = 0;
	}

	var cpf = ''+n1+n2+n3+n4+n5+n6+n7+n8+n9+d1+d2;
	var valueToType = cpf;

    this.page().replaceText(element, valueToType);
};

	
Selenium.prototype.doGeneratePassword = function (locator, text) {
	var element = this.page().findElement(locator);
	var length = text;
	
	var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$&*-_";
	var password = "";
		for(x=0;x<length;x++) {
			i = Math.floor(Math.random() * 70);
			password += chars.charAt(i);
		}
	
	this.page().replaceText(element, password);
};

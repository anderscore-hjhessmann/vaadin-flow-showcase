import {html, LitElement} from "lit-element";

class MyHelloElement extends LitElement {

    greet : string | undefined;

    render() {
        return html`
            <h3>Hello ${this.greet}</h3>
        `;
    }
}

window.customElements.define('my-hello-element', MyHelloElement);
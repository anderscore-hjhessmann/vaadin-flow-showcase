import {customElement, html, LitElement, property} from "lit-element";

@customElement("my-hello-element")
class MyHelloElement extends LitElement {

    @property()
    greet = "World"

    render() {
        return html`
            <h3>Hello ${this.greet}!</h3>
        `;
    }
}

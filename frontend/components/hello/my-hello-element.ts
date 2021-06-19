import {customElement, html, LitElement, property, css} from "lit-element";

@customElement("my-hello-element")
class MyHelloElement extends LitElement {

    static get styles() {
        return css`
            .greeting {
                background-color: green;
                color: blue;
                padding: 0.5rem;
            }
        `;
    }

    @property()
    greet = "World"

    render() {
        return html`
            <h3 class="greeting">Hello ${this.greet}!</h3>
        `;
    }
}

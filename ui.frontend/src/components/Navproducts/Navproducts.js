import React, { Component } from 'react';
import { MapTo } from '@adobe/aem-react-editable-components';

export const NavProductsEditConfig = {

    emptyLabel: 'NavProducts',

    isEmpty: function (props) {
        return !props || !props.src || props.src.trim().length < 1;
    }
};


class NavProducts extends Component {
    render() {
        return (
            <><div>My Text Property Value: {this.props.text}</div>
                <p>Hello World from REACT</p>

            </>
        );
    }
}

MapTo('vivopoc/components/navproducts')(NavProducts, NavProductsEditConfig);

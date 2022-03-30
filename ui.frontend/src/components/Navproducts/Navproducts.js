import React, { 
    Component,
    withComponentMappingContext } from 'react';
import { MapTo } from '@adobe/aem-react-editable-components';

const BasicComponentEditConfig = {
    emptyLabel: 'Basic Component',
    isEmpty: function(props) {
        return !props || props.text;
    }
};

class NavProducts extends Component {
    render() {
        return(
            <div>My Text Property Value: {this.props.text}</div>
        );
    }
}

export default NavProducts
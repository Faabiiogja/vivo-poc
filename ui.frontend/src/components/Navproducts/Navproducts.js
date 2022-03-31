import React, { 
    Component,
     } from 'react';


class NavProducts extends Component {
    render() {
        return(
            <><div>My Text Property Value: {this.props.text}</div>
            <p>Hello World from REACT</p>
            
            </>
        );
    }
}

export default NavProducts
import {React} from 'react';
import {Link} from 'react-router-dom';
import './YearSelector.scss';

export function YearSelector({teamName}) {

    const years = [];

    for(var i=2008;i<=2020;i++){
        years.push(i);
    }

    return (
        <div className="YearSelector">
            {years.map(y=> <h5 key={y} className="list-element"><Link to = {`/team/${teamName}/year/${y}`}>{y}</Link></h5>)}
        </div>
        );
}
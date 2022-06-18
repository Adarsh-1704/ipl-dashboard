import {React} from 'react';
import {Link} from 'react-router-dom';
import './TeamTile.scss';
import {Logo} from './Logo';
export function TeamTile({teamName}) {

    return (
        <div className="TeamTile">
            <div className="team-name-section">
                <Link to={`/team/${teamName}`}>{teamName}</Link>
            </div>
            <div className="logo-section">
                <Logo teamName={teamName}/>
            </div>
        </div>
        );
}
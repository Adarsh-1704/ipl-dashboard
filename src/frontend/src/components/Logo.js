import {React} from 'react';

export function Logo({teamName}) {

    return (
        <div className="Logo">
            <img key={teamName} src={require(`../logo/${teamName}.png`)} width="190" height="100" alt="team-logo" />
        </div>
        );
}
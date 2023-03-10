import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
    const [data, setData] = useState(null);
    const [cpuInfo, setCpuInfo] = useState('');
    const [gpuInfo, setGpuInfo] = useState('')

    useEffect(() => {
        // CPU 정보 가져오기
        const cpu = navigator.hardwareConcurrency;
        setCpuInfo(`CPU 모델명: ${cpu}`);

        // WebGL을 사용하여 GPU 정보 가져오기
        const canvas = document.createElement('canvas');
        const gl = canvas.getContext('webgl') || canvas.getContext('experimental-webgl');
        if (gl) {
            const info = gl.getExtension('WEBGL_debug_renderer_info');
            if (info) {
                const gpu = gl.getParameter(info.UNMASKED_RENDERER_WEBGL);
                setGpuInfo(`GPU 모델명: ${gpu}`);
            } else {
                setGpuInfo('GPU 정보를 가져올 수 없습니다.');
            }
        } else {
            setGpuInfo('WebGL을 지원하지 않는 브라우저입니다.');
        }
    }, []);

    const handleClick = async () => {
        try {
            const response = await axios.get("http://localhost:12000/api/data");
            console.log("받은 데이터:", response.data);
            setData(response.data);
        } catch (error) {
            console.error(error);
            // TODO: 오류 처리 로직을 추가
        }
    };

    return (
        <div>
            <button onClick={handleClick}>Send Request to Spring Boot Server</button>
            {data && (
                <ul>
                    {data.map((item) => (
                        <li key={item.id}>{item.name}</li>
                    ))}
                </ul>
            )}
            {cpuInfo !== null && <p>CPU 정보: {cpuInfo}</p>}
            {gpuInfo !== null && <p>GPU 정보: {gpuInfo}</p>}
        </div>
    );
}

export default App;
